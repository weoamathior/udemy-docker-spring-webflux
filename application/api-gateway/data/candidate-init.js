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
        _id: "1",
        name: "Winston",
        skills: [ "java", "spring", "docker" ],
    },
    {
        _id: "2",
        name: "Mike",
        skills: [ "k8s", "node", "docker" ],
    },
    {
        _id: "3",
        name: "Sally",
        skills: [ "jira" ],
    }
]);
