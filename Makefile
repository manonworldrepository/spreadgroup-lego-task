install:
	docker compose up -d --build;
run:
	docker exec -it spreadgroup mvn clean package;
	docker exec -it spreadgroup java -jar target/lego-1.0-SNAPSHOT.jar server;
clean:
	docker compose down && docker compose rm
