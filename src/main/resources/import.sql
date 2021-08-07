insert into kitchen (id, name) values (1, 'Thai');
insert into kitchen (id, name) values (2, 'Indian');

insert into restaurant (id, name, tax_shipping, kitchen_id) values (1, 'Thai Gourmet', 11.90, 1);
insert into restaurant (id, name, tax_shipping, kitchen_id) values (2, 'Tuk tuk Indian Food', 12.90, 2);

insert into state (name) values ('São Paulo');
insert into state (name) values ('Rio de Janeiro');
insert into state (name) values ('Amazonas');

insert into city (name, state_id) values ('Piracicaba', 1);
insert into city (name, state_id) values ('Paraty', 2);
insert into city (name, state_id) values ('Manaus', 3);

insert into payment (description) values ('Cartão de Crédito');
insert into payment (description) values ('Dinheiro');

insert into permission (name, description) values ('Usuário comum', 'Taxas e frete cobrados normalmente');
insert into permission (name, description) values ('Funcionário', 'Taxas e frete gratuitos');