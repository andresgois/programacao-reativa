db = db.getSiblingDB('webfluxdb');
db.createUser(
    {
        user: "root",
        pwd: "123456",
        roles: [
            {
                roles: [
                    { role: "readWrite", db: "webfluxdb" }
                ],
                db: "webfluxdb"
            }
        ]
    }
);
db.createCollection("Acessos");