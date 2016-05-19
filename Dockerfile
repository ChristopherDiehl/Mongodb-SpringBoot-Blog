FROM java:8
VOLUME /tmp
ADD target/Blog-0.0.1-SNAPSHOT.jar blog.jar
EXPOSE 8080
RUN bash -c 'touch /blog.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb/micros", "-Djava.security.egd=file:/dev/./urandom","-jar","/blog.jar"]
