docker run --rm \
	--name postgres \
	-e POSTGRES_USER=sa \
	-e POSTGRES_PASSWORD=123 \
	-e PGDATA=/var/lib/postgresql/data/pgdata \
	-p 5432:5432 \
	-v /home/mateusgobo/dockerdata/postgres:/var/lib/postgresql/data \
	-d postgres