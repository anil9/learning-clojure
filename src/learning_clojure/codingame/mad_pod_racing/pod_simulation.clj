(ns learning-clojure.codingame.mad-pod-racing.pod-simulation
  (:require [clojure.math :as math]))
            ;[clojure.tools.trace :as tools]))

(defn distance [[x1 y1] [x2 y2]]
  (Math/sqrt (+ (Math/pow (- x1 x2) 2) (Math/pow (- y1 y2) 2))))

(defn angle [[x1 y1] [x2 y2]]
  (let [d (distance [x1 y1] [x2 y2])
        dx (/ (- x2 x1) d)
        dy (/ (- y2 y1) d)
        a (math/to-degrees (math/acos dx))]
    (if (< dy 0) ; point is below, shift the angle
      (- 360 a)
      a)))
(comment
  (angle [0 9000] [9000 0]))
  
(defn diff-angle [target-angle current-angle]
  (let [right (if (<= current-angle target-angle) (- target-angle current-angle) (- 360 (+ current-angle target-angle)))
        left (if (>= current-angle target-angle) (- current-angle target-angle) (- (+ current-angle 360) target-angle))]
      (if (< right left) right (- left))))

(defn simple-mod [n d]
  (cond 
    (>= n d) (- n d)
    (< n 0) (+ n d)
    :else n))
    

(defn rotate-towards-target [previous-angle source target]
  (let [diff (diff-angle (angle source target) previous-angle)]
    (simple-mod (+ previous-angle
                  (cond
                    (> diff 0) (- diff (min diff 18))
                    (< diff 0) (- diff (max diff -18))
                    :else 0)) 360)))

(defn boost [pod thrust]
  (let [rad (math/to-radians (:angle-to-checkpoint pod))]
    (-> pod
        (update :vx #(+ % (* (math/cos rad) thrust)))
        (update :vy #(+ % (* (math/sin rad) thrust))))))
  
(comment
  (math/cos (math/to-radians 301.47))
  (math/sin (math/to-radians 301.47)))
  
(defn move [pod]
  (update pod :point (fn [[x y]] (mapv int [(+ x (get pod :vx)) (+ y (get pod :vy))]))))

(defn frictionate [v]
  (math/round (* v 0.85)))

(defn pod-point [board]
  (get-in board [:pod :point]))

(defn target-point [board]
  (get-in board [:target :point]))

(defn distance-to-target [board]
  (distance (pod-point board) (target-point board)))

(defn reached-target? [board]
  (< (distance-to-target board) (get-in board [:target :radius])))

(def initial-game
  {:pod {:vx 0
         :vy 0
         :point [100 100]
         :remaining-turns 100}
   :target {:point [5000 2000]
            :radius 600}})

(defn initiate-board [[px py] [tx ty]]
  (-> initial-game
      (assoc-in [:pod :point] [px py])
      (assoc-in [:target :point] [tx ty])
      (assoc-in [:pod :angle-to-checkpoint] (angle [px py] [tx ty]))))

(comment
  (initiate-board [0 0] [100 100]))
  

(defn tick
  "In a tick each pod will do the following things in order:
   *  The pod will turn towards its target. If the pod wants to turn by more than 18° to face its target, it only turns by 18°.
   *  We then apply the requested thrust to the speed of the pod (between 0 and 100)
   *  The pod moves according to its new speed
   *  We reduce the speed of the pod by 15%, in other words we multiply the speed by 0.85
   *  The x and y attributes of the pod are rounded to the nearest integer
   *  The vx and vy attributes of the pod are truncated
  "

  [board thrust]
  (-> board
    (update-in [:pod :angle-to-checkpoint] rotate-towards-target (pod-point board) (target-point board))
    (update :pod boost thrust)
    (update :pod move)
    (update-in [:pod :vx] frictionate)
    (update-in [:pod :vy] frictionate)
    (update-in [:pod :remaining-turns] dec)))

(comment 
  (tick initial-game 10)
  (tick (tick initial-game 10) 5)
  (math/to-radians (get-in initial-game [:pod :angle-to-checkpoint]))
  (take 30 (iterate #(tick % 1) initial-game))
  (angle (pod-point initial-game) (target-point initial-game)))
