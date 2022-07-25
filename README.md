# SpringDemo
## 初次使用
- podman machine init
- podman machine start
- podman build --tag iisi-demo -f ./Dockerfile
- podman run -p 8080:8080 --name iisi-demo --rm iisi-demo
- http://localhost:8080/swagger-ui/index.html## 再次啟動
- podman start iisi-demo
## 停止
- podman stop iisi-demo
