(ns learning-clojure.codingame.pod-simulation
  (:require [clojure.math :as math]
            [clojure.tools.trace :as tools]))

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
  
(defn rotate-towards-target [angle-to-checkpoint source target]
  (let [diff (angle source target)]
    (cond
      (> diff 0) (mod (- diff (min diff 18)) 360)
      (< diff 0) (mod (- diff (max diff -18)) 360)
      :else 0)))

(defn boost [pod thrust]
  (let [rad (math/to-radians (:angle-to-checkpoint pod))]
    (-> pod
        (update :vx #(+ % (* (math/cos rad) thrust)))
        (update :vy #(+ % (* (math/sin rad) thrust))))))
  
(defn move [pod]
  (update pod :point (fn [[x y]] (tools/trace (mapv int [(+ x (get pod :vx)) (+ y (get pod :vy))])))))

(defn frictionate [v]
  (math/round (* v 0.85)))

(defn pod-point [board]
  (get-in board [:pod :point]))

(defn target-point [board]
  (get-in board [:target :point]))

(def initial-game
  {:pod {:vx 0
         :vy 0
         :point [100 100]
         :remaining-turns 100
         :angle-to-checkpoint (angle (pod-point initial-game) (target-point initial-game))}
   :target {:point [70 10]
            :radius 600}})


(defn tick
  "In a tick each pod will do the following things in order:
   *  The pod will turn towards its target. If the pod wants to turn by more than 18° to face its target, it only turns by 18°.
   *  We then apply the requested thrust to the speed of the pod (between 0 and 200)
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
  (math/to-radians (get-in initial-game [:pod :angle-to-checkpoint]))
  (take 30 (iterate #(tick % 1) initial-game))
  (angle (pod-point initial-game) (target-point initial-game)))
