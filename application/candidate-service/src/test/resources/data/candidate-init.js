// create db
db = db.getSiblingDB('candidate');

// create user
db.createUser({
    user: "candidate_user",
    pwd: "candidate_password",
    roles:[
        {
            role: "readWrite",
            db: "candidate"
        }
    ]
});

// create collection
db.createCollection('candidate');

// create docs
db.candidate.insertMany([
    {
        name: "Winston",
        skills: [ "java", "spring", "docker" ],
    }
]);
