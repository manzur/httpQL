**This project aims to bring SQL and HTTP world together.** 

#### Two facts form the base this project:
- Internet is a big *database* that contains lots of pages

- Web-server is nothing more than *interpretator* that accepts http protocol as a language and produces a web page

So by concluding these two facts it occurs that sql language can be used to model web-client colloboration on top of http.

Let's see some examples how SQL could be used(further on this customized SQL is called httpQL):


### Simple queries 

<table border="1">
    <thead>
        <th>HTTP</th>
        <th>httpQL</th>
    </thead>
    <tr>
        <td>GET /index.html HTTP/1.1</td>
        <td>select * from index.html</td>
    </tr>
    <tr>
        <td>PUT /index.html HTTP/1.1<p/>"File contents"</td>
        <td>insert into index.html values(&quot;File contents&quot;)</td>
    </tr>
    <tr>
        <td>POST /index.html HTTP/1.1<p/>&quot;param1=value1&amp;param2=value2&quot;</td>
        <td>update index.html set param1 = value1 and param2 = value2</td>
    </tr>
    <tr>
        <td>DELETE /index.html HTTP/1.1</td>
        <td>delete * from index.html</td>
    </tr>
</table>




### More sophisticated queries 
Select queries can be used to combine http GET request and xpath together. Let's see examples(GET requests in http column are omitted):

<table border="1">
    <thead>
        <th>XPATH</th>
        <th>httpQL</th>
    </thead>
    <tr>
        <td>//book</td>
        <td>select book from index.html</td>
    </tr>
    <tr>
        <td>/bookstore</td>
        <td>select .bookstore from index.html</td>
    </tr>
    <tr>
        <td>//bookstore/book</td>
        <td>select bookstore.book from index.html</td>
    </tr>
    <tr>
        <td>//bookstore//book</td>
        <td>select bookstore..book from index.html</td>
    </tr>
    <tr>
        <td>//bookstore/book[1]</td>
        <td>select .bookstore.book from index.html where limit book by 1</td>
    </tr>
    <tr>
        <td>//bookstore/book[@position &lt; 3]</td>
        <td>select .bookstore.book from index.html where book@position &lt; 3</td>
    </tr>
    <tr>
        <td>//bookstore/book[@position=@last()]</td>
        <td>select .bookstore.book from index.html where book@position = @last</td>
    </tr>
    <tr>
        <td>//title[@lang]</td>
        <td>select title from index.html where title@lang=*</td>
    </tr>
    <tr>
        <td>//title[@lang='eng']</td>
        <td>select title from index.html where title@lang='eng'</td>
    </tr>
    <tr>
        <td>//bookstore/book[@price &gt; 35]</td>
        <td>select .bookstore.book from index.html where book@price &gt; 35</td>
    </tr>
    <tr>
        <td>//bookstore/book[@price &gt; 35]/title</td>
        <td>select .bookstore.book.title from index.html where book@price &gt; 35</td>
    </tr>
    <tr>
        <td>//bookstore/book/title</td>
        <td>select .bookstore.book.title from index.html </td>
    </tr>
    <tr>
        <td>//bookstore/book/price/text()</td>
        <td>select .bookstore.book.price@text from index.html</td>
    </tr>
    <tr>
        <td>//host/service[text='DNS']</td>
        <td>select host.service from index.html where service@text() = 'DNS'</td>
    </tr>
    <tr>
        <td>//network/host/interface/arec/text()</td>
        <td>select network.host.interface.arec@text() from index.html limit host by 2</td>
    </tr>
    <tr>
        <td>//*[speciality]</td>
        <td>select * from index.html where @speciality=*</td>
    </tr>
    <tr>
        <td>//degree[@from='Harvard']</td>
        <td>select degree from index.html where degree@from='Harvard'</td>
    </tr>
    <tr>
        <td>//author[text()='Bob Martin']</td>
        <td>select author from index.html where author@text() = 'Bob Martin'</td>
    </tr>
    <tr>
        <td>//author[firstname='Bob' and lastname='Martin']</td>
        <td>select author from index.html where author@firstname='Bob' and author@lastname='Martin'</td>
    </tr>
    <tr>
        <td>//*[id='....']/uil/li[1]/div[2]/p[1]/a</td>
        <td>select *.ul.li.div.p.a from index.html where *@id='w3c_home_upcoming_events' and limit li by 1 and limit div by 2 and limit p by 1</td>
    </tr>
    <tr>
        <td>//ul[1]//ul[2]</td>
        <td>select ul..ul from index.html where limit ul#1 by 1 and limit ul#2 by 2</td>
    </tr>

</table>





#### Post-mortem:

Tables give impression that XPATH is simpler and it really is, httpQL contains some artifacts(such #) and less readible. Seperation of concerns did a great job for HTTP and XPath world.
 
