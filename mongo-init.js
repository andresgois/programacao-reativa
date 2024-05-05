db = db.getSiblingDB('curso_webflux');
db.createUser(
    {
        user: "andre",
        pwd: "123456",
        roles: [
            {
                role: "readWrite",
                db: "webfluxdb"
            }
        ]
    }
);
db.createCollection("Acessos");