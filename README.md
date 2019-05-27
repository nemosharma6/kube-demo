### kube-demo : playing with kubernetes

download docker for desktop. go to preferences and start a kubernetes cluster

#### kubernetes 101
https://www.youtube.com/watch?v=H-FKBoWTVws   

#### build and push image to docker hub
sbt compile   
sbt docker    
docker login --username=<user_name> --password=<your_password>   
docker tag <image_id> <repo_name_like -> nimish6/kube-demo>   
docker push <repo_name>   

#### setup context
kubectl config use-context docker-for-desktop   

#### setup redis master and slave. yaml files contained in kube folder.
kubectl apply -f redis-master.deployment.yaml   
kubectl apply -f redis-slave.deployment.yaml    
kubectl apply -f redis-master.service.yaml        
kubectl apply -f redis-slave.service.yaml   

#### setup app
kubectl apply -f app.deployment.yaml    
kubectl apply -f app.service.yaml   

#### some commands to check the cluster
kubectl get deployments   
kubectl get pods    
kubectl get services (the port in the app is mapped to another port in order to access the service)   
kubectl exec -it <pod_id> -- /bin/bash (here a pod has only 1 container running)    

#### deletion
kubectl delete deployment <deployment_name>   
kubectl delete service <service_name>   
