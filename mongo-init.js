db = db.getSiblingDB('webflux');

db.createUser({
    user: "andre",
    pwd: "123456",
    roles: [
        { role: "readWrite", db: "webflux" }
    ]
});

db.createCollection("Acessos");
