**This project aims to bring SQL and HTTP world together.** 

#### Two facts form the base this project:
- Internet is a big *database* that contains lots of pages

- Web-server is nothing more than *interpretator* that accepts http protocol as a language and produces a web page

So by concluding these two facts we came to that sql language can be used to model web-client colloboration on top of http.

Let's see some examples how SQL could be used(further on this customized SQL is called httpQL):

#### Select query:

- retrieve whole contents of index.html
  
  `select * from index.html`

- retrieve body contents of the page
   
  `select body from index.html`

- retrieve all unordered lists from the page
    
  `select ul from index.html`

- retrieve unordered lists that have class "invisible"
    
  `select ul from index.html where ul.class = "invisible"`

- retrieve the contents of the p tag with id "1"
    
  `select p._value from index.html where p.id = "1"`

- make head query to index.html page
    
  `select _head from index.html`


#### Update query:

- make post query:
   
        update index.html
               set name="myname"
               bankId="mybank"
               cardId="0777"
         where form="myform"
  

- make put query:

        update index.html 
               set _contents = "Contents of the file1
                                line1
                                line2
                                lineN" 
                          

#### Delete query:

- delete will support only the removal of one whole file and nothing more:

     `delete * from index.html`
        

    







