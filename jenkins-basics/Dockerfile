FROM jenkins/jenkins:lts

USER root
RUN apt-get update && apt-get install -y unzip wget && \
    wget https://services.gradle.org/distributions/gradle-7.4.2-bin.zip && \
    unzip gradle-7.4.2-bin.zip -d /opt && \
    ln -s /opt/gradle-7.4.2/bin/gradle /usr/bin/gradle && \
    rm gradle-7.4.2-bin.zip

USER jenkins