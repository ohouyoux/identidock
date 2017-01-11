FROM java:8-jre

MAINTAINER Olivier Houyoux "olivier.houyoux@gmail.com"

# Change user to avoid running the container with root privileges
RUN groupadd -g 999 identidock && useradd -r -g identidock identidock

VOLUME /tmp

ADD /target/identidock-0.1.0.jar app.jar

RUN sh -c 'touch /app.jar'

# Defang the setuid and setgid binaries to avoid privilege escalation attacks
RUN find / -perm +6000 -type f -exec chmod a-s {} \; || true

# Change user to avoid running the container with root privileges
USER identidock

ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]