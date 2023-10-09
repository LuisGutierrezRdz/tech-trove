print('############################# Start #############################');

db = db.getSiblingDB('product-db');
db.createUser(
    {
        user: 'tech-trove-product',
        pwd: 'B8czpu24',
        roles: [
            { role: 'readWrite', db: 'product-db' }],
    },
);
db.createCollection('metadata');

db = db.getSiblingDB('order-db');
db.createUser(
    {
        user: 'tech-trove-order',
        pwd: 'rnY3MLr9',
        roles: [
            { role: 'readWrite', db: 'order-db' }],
    },
);
db.createCollection('metadata');

db = db.getSiblingDB('user-db');
db.createUser(
    {
        user: 'tech-trove-user',
        pwd: 'qj4peQLq',
        roles: [
            { role: 'readWrite', db: 'user-db' }],
    },
);
db.createCollection('metadata');

print('############################# End #############################');