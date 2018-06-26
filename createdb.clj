(require '[clojure.java.jdbc :as jdbc])
(jdbc/with-db-connection [conn {:dbtype "h2" :dbname "./clojure-webapp"}]

  (jdbc/db-do-commands conn
    [(jdbc/create-table-ddl :locations
      [[:id "bigint primary key auto_increment"]
       [:x "integer"]
       [:y "integer"]])
    (jdbc/create-table-ddl
     :person
     [[:person_id "bigint primary key auto_increment"]
      [:name "varchar(200)"]
      [:rg "varchar(200)"]
      [:cpf "varchar(200)"]])
     (jdbc/create-table-ddl
     :product
     [[:product_id "bigint primary key auto_increment"]
      [:name "varchar(200)"]
      [:type "enum ('book', 'cd')"]
      [:number :int]])
    (jdbc/create-table-ddl
     :transaction
     [[:transaction_id "bigint primary key auto_increment"]
      [:type "enum ('purchase', 'donation')"]
      [:comments "varchar(400)"]
      [:product_id "bigint references product(product_id)"]
      [:person_id "bigint references person(person_id)"]])]))
