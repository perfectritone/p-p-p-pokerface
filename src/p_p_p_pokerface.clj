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

(defn pair? [hand]
  (contains? 2 (vals (frequencies (map rank hand)))))

(defn three-of-a-kind? [hand]
  nil)

(defn four-of-a-kind? [hand]
  nil)

(defn flush? [hand]
  nil)

(defn full-house? [hand]
  nil)

(defn two-pairs? [hand]
  nil)

(defn straight? [hand]
  nil)

(defn straight-flush? [hand]
  nil)

(defn value [hand]
  nil)
