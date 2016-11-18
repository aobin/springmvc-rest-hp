# springmvc-rest
it provides restful services by spring mvc framework, mainly focus on rss feed info.
it will load rss document from http://myprintly.com/rss when the server is starting and keep the feed document into memcache.
it provides restful api as http://localhost:8080/feedItems?limit=2&offset=0(Get Request) and returns feed information in json format.
the memcache info has been configured in memcache.properties.
