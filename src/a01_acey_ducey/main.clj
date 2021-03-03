(ns a01-acey-ducey)

(def deck (range 1 14))

(defn card-to-face
  [card]
  (condp = card
    11 "Jack"
    12 "Queen"
    13 "King"
    14 "Ace"
    card))

(defn game-loop
  ([] (game-loop 100))
  ([cash]
   (if (<= cash 0)
     (println "Sorry, my friend, but you blew your wad!!")
     (let [[first-card second-card] (sort < [(rand-nth deck) (rand-nth deck)])]
       (println)
       (println "You have" cash "dollars in cash.")
       (println)
       (println "Here are your next two cards")
       (println)
       (println (card-to-face first-card))
       (println (card-to-face second-card))
       (println)
       (let [bet (Integer/parseInt (do
                                    (print "What is your bet in dollars that the next card will be between the two?")
                                    (flush)
                                    (read-line)))
             third-card (rand-nth deck)]
         (println)
         (println "My next card was a" (card-to-face third-card))
         (println)
         (cond
          (= bet 0) (println "Chicken!!")
          (> bet cash) (do
                        (println "Sorry, my friend but you have bet too much!!")
                        (recur cash))
          (< first-card third-card second-card) (do
                                                 (println "You win!!")
                                                 (recur (+ cash (* bet 2))))
          :else (do
                 (println "Sorry, you loose!!")
                 (recur (- cash bet)))))))))
