(ns p-p-p-pokerface)

(defn suit [[_ suit_var]]
  (str suit_var))

(defn rank [[rank_var _]]
  (if (Character/isDigit rank_var)
    (Integer/valueOf (str rank_var))
    (let [exceptional_ranks {\T 10
                             \J 11
                             \Q 12
                             \K 13
                             \A 14}]
      (get exceptional_ranks rank_var))))

(defn card-frequencies [hand]
  (vals (frequencies (map rank hand))))

(defn pair? [hand]
  (>= (apply max (card-frequencies hand)) 2))

(defn three-of-a-kind? [hand]
  (>= (apply max (card-frequencies hand)) 3))

(defn four-of-a-kind? [hand]
  (>= (apply max (card-frequencies hand)) 4))

(defn flush? [hand]
  (== 1 (count (set (map suit hand)))))

(defn full-house? [hand]
  (=
    [2 3]
    (sort
      (card-frequencies hand))))

(defn two-pairs? [hand]
  (=
    [1 2 2]
    (sort
      (card-frequencies hand))))

(defn iterative-seq? [i-seq]
  (= (range (first i-seq) (inc (last i-seq))) i-seq))

(defn straight? [hand]
  (or
    (iterative-seq? (sort (map rank hand)))
    (iterative-seq? (sort (replace {14 1} (map rank hand))))))

(defn straight-flush? [hand]
  (and (straight? hand) (flush? hand)))

(defn value [hand]
  (cond
    (straight-flush? hand) 8
    (four-of-a-kind? hand) 7
    (full-house? hand) 6
    (flush? hand) 5
    (straight? hand) 4
    (three-of-a-kind? hand) 3
    (two-pairs? hand) 2
    (pair? hand) 1
    :else 0))
