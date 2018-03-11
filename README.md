# KafkaSamples
Kafka Java project with some examples


download kafka
unpack tarball
copy server properties files from the resources folder in this repository to the config folder of your unpacked kafka folder
cd to the base of the kafka folder

open one terminal window and run the following to start zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

open three terminal windows, one for each of the three brokers
Run the following to start the brokers:
bin/kafka-server-start.sh config/server-0.properties
bin/kafka-server-start.sh config/server-1.properties
bin/kafka-server-start.sh config/server-2.properties

Run the following to start a consumer (in another terminal window), i like to do this directly in the Java IDE
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my_topic --from-beginning

Import this Java project into your IDE of choice and run.
You should get a an output of how ever many messages your producer sent in your consumer terminal window
