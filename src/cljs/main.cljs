(ns pasta.main
  (:require [enfocus.core :as ef]
            [clojure.browser.repl :as repl]
            [clojure.string :as string])
  (:require-macros [enfocus.macros :as em])
  (:use [jayq.core :only [$ css inner]]))

;;
;; Consts
;;

(def enter-key-code 13)

;;
;; Snippets
;;

(em/defsnippet frag-message "/html/fragments.html" [:#single-message]
  [msg link]
  [:p] (em/content msg)
  [:img] (em/do-> (em/set-attr :src (str link))
                  (em/content link)))

;;
;; Functions
;;

(defn escape [text]
  (string/replace text #"[^a-zA-Z0-9]" ""))
  
(defn get-message []
  (let [msg (em/from js/document
                     :message [:#message-text] (em/get-prop :value))]
    (str (:message msg))))

(defn make-link [msg]
  (str "http://" (js/encodeURI (escape msg)) ".jpg.to/medium+r"))

;;
;; Interface
;;

(defn ^:export process-message [msg]
  (em/at js/document
         ["#message-stack"] (em/prepend (frag-message msg (make-link msg)))))

(defn ^:export reset-all []
  (em/at js/document
         ["#message-stack"] (em/content (str ""))))

(defn ^:export paste []
  (process-message (get-message)))

(defn ^:export handle-keypress [event]
  (when (= enter-key-code (.-keyCode event))
    (paste)
    (.preventDefault event)))

;;
;; Actions
;;

(em/defaction setup []
  ["#message-text"] (em/listen :keypress handle-keypress)
  ["#add-button"] (em/listen :click #(paste))
  ["#reset-button"] (em/listen :click #(reset-all)))

(set! (.-onload js/window) setup)