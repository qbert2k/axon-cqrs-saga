docker run --name axonserver \
-p 8024:8024 -p 8124:8124 \
-v "~/Projects/axon-cqrs-saga/docker-data/data":/data \
-v "~/Projects/axon-cqrs-saga/docker-data/eventdata":/eventdata \
-v "~/Projects/axon-cqrs-saga/docker-data/config":/config \
axoniq/axonserver 
