(ns learning-clojure.codingame.mad-pod-racing.ui
  (:require [learning-clojure.codingame.mad-pod-racing.pod-simulation :as simulation]
            [learning-clojure.codingame.mad-pod-racing.ai :as ai]))

(def fpg (atom nil))


(defn close! []
  (when-let [f (:frame @fpg)]
     (.setVisible f false)
     (.dispose f)
     (reset! fpg nil)))

(defn open! [title width height]
  (if @fpg (do (close!) (open! title width height))
   (let [f (javax.swing.JFrame. title)
         _ (.setSize f width (+ 24 height))
         c (.getContentPane f)
         p (javax.swing.JPanel.)
         _ (.add c p)
         _ (.setVisible f true)
         g (.getGraphics p)
         i (.createImage p width height)
         h (.getGraphics i)]
     (reset! fpg {:frame f :panel p :graphics g
                  :oi i :og h}))))
(comment
  (open! "simulation" 50 50)
  (close!))


(defn display []
  (open! "Simulation" 16000 9000)
  (let [initial-board (simulation/initiate-board [0 0] [100 100])]
    (doseq [tick (take 100 (iterate #(simulation/tick % 1) initial-board))]
      (doto (:og @fpg)
        (.setColor (. java.awt.Color BLACK))
        (.fillRect (* 10 (first (simulation/pod-point tick))) (* 10 (second (simulation/pod-point tick))) 10 10) ; each cell is a 10x10 rectangle
        (.setColor (. java.awt.Color GREEN))
        (.fillRect (* 10 (first (simulation/target-point tick))) (* 10 (second (simulation/target-point tick))) 10 10)) ; each cell is a 10x10 rectangle
      (.drawImage (:graphics @fpg) (:oi @fpg) 0 0 (:panel @fpg))
      (Thread/sleep 300))))

(comment
  (display)

  (let [initial-board (simulation/initiate-board [100 100] [1000 1000])]
    (take 10 (iterate #(simulation/tick % (first (:thrust (ai/highest-scoring-input % 10 10)))) initial-board)))
  (doto (:og @fpg)
    (.setColor (. java.awt.Color BLACK))
    (.fillRect (* 10 50) (* 10 50) 1000 1000))
  (.drawImage (:graphics @fpg) (:oi @fpg) 0 0 (:panel @fpg)))
