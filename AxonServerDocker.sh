docker run --name axonserver \
-p 8024:8024 -p 8124:8124 \
-v "/home/javier/Projects/Axon, CQRS, SAGA/docker-data/data":/data \
-v "/home/javier/Projects/Axon, CQRS, SAGA/docker-data/eventdata":/eventdata \
-v "/home/javier/Projects/Axon, CQRS, SAGA/docker-data/config":/config \
axoniq/axonserver 
