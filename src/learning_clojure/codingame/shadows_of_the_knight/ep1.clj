(ns learning-clojure.codingame.shadows-of-the-knight.ep1)

(defn vec+ [v1 v2]
  (mapv + v1 v2))

(defn up-or-down [c]
  (case c \U -1 \D 1 nil))

(defn right-or-left [c]
  (case c \R 1 \L -1 nil))

(defn translate [direction]
  (if (= 2 (count direction))
    [(right-or-left (second direction)) (up-or-down (first direction))]
    (let [ud (up-or-down (first direction))
          rl (right-or-left (first direction))]
      (if (nil? ud) [rl 0] [0 ud]))))

(defn x [coord]
  (first coord))

(defn y [coord]
  (second coord))

(defn apply-direction [from direction rec]
  (let [marker (vec+ from (translate direction))
        nw (:nw rec)
        ne (:ne rec)
        sw (:sw rec)
        se (:se rec)]
    (case direction
      "DL" {:nw [(x nw) (y marker)] :ne marker
            :sw sw :se [(x marker) (y se)]}
      "UL" {:nw nw :ne [(x marker) (y ne)]
            :sw [(x sw) (y marker)] :se marker}
      "UR" {:nw [(x marker) (y nw)] :ne ne
            :sw marker :se [(x se) (y marker)]}
      "DR" {:nw marker :ne [(x ne) (y marker)]
            :sw [(x marker) (y sw)] :se se}
      "D" {:nw marker :ne marker
           :sw [(x marker) (y sw)] :se [(x marker) (y se)]}
      "U" {:nw [(x marker) (y nw)] :ne [(x marker) (y ne)]
           :sw marker :se marker}
      "L" {:nw [(x nw) (y marker)] :ne marker
           :sw [(x nw) (y marker)] :se marker}
      "R" {:nw marker :ne [(x ne) (y marker)]
           :sw marker :se [(x se) (y marker)]})))

(defn init-rec [w h]
  {:nw [0 0]
   :ne [(dec w) 0]
   :sw [0 (dec h)]
   :se [(dec w) (dec h)]})

(defn middle [a b]
  (int (/ (+ a b) 2)))

(defn center-coord [rec]
  (let [x (middle (first (:se rec)) (first (:sw rec)))
        y (middle (second (:sw rec)) (second (:nw rec)))]
    [x y]))

(comment
  (first "N")
  (count "NE")
  (up-or-down \L)
  (translate "DL")
  (middle 5 3)
  (middle 3 5)
  (center-coord (init-rec 8 8))
  (apply-direction [6 2] "DR" (apply-direction [2 5] "UR" (init-rec 10 10))))
   
