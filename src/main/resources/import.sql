DELETE FROM pedido_produto;
DELETE FROM pedido;
DELETE FROM cliente;
DELETE FROM produto;
COMMIT;

ALTER SEQUENCE public.cliente_id_seq RESTART WITH 1;
ALTER SEQUENCE public.pedido_id_seq RESTART WITH 1;
ALTER SEQUENCE public.produto_id_seq RESTART WITH 1;
COMMIT;

--Cria os clientes para testes
INSERT INTO cliente (cpf, nome) VALUES (12345678901, 'João da Silva');
INSERT INTO cliente (cpf, nome) VALUES (98765432109, 'Maria Souza');
INSERT INTO cliente (cpf, nome) VALUES (45678912345, 'Pedro Almeida');
INSERT INTO cliente (cpf, nome) VALUES (78901234567, 'Ana Santos');
INSERT INTO cliente (cpf, nome) VALUES (34567890123, 'Lucas Oliveira');
INSERT INTO cliente (cpf, nome) VALUES (89012345678, 'Mariana Costa');
INSERT INTO cliente (cpf, nome) VALUES (56789012345, 'Carlos Pereira');
INSERT INTO cliente (cpf, nome) VALUES (90123456789, 'Juliana Rodrigues');
INSERT INTO cliente (cpf, nome) VALUES (23456789012, 'Gustavo Fernandes');
INSERT INTO cliente (cpf, nome) VALUES (67890123456, 'Laura Mendes');
INSERT INTO cliente (cpf, nome) VALUES (34567891234, 'Fernando Silva');
INSERT INTO cliente (cpf, nome) VALUES (78912345678, 'Camila Santos');
INSERT INTO cliente (cpf, nome) VALUES (56789123450, 'Rafaela Oliveira');
INSERT INTO cliente (cpf, nome) VALUES (90123456780, 'Rodrigo Pereira');
INSERT INTO cliente (cpf, nome) VALUES (23456789010, 'Amanda Rodrigues');
COMMIT;

--Cria os lanches
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Cheeseburger', 9.99, 'LANCHE');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Hambúrguer Vegano', 12.99, 'LANCHE');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Hambúrguer de Frango', 7.99, 'LANCHE');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Hambúrguer Gourmet', 5.99, 'LANCHE');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Hambúrguer com Queijo Cheddar', 4.99, 'LANCHE');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Hambúrguer com Bacon', 8.99, 'LANCHE');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Hambúrguer com Cebola Caramelizada', 6.99, 'LANCHE');
COMMIT;
--Cria os acompanhamentos
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Batata Frita', 7.99, 'ACOMPANHAMENTO');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Onion Rings', 6.99, 'ACOMPANHAMENTO');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Batata Rústica', 3.99, 'ACOMPANHAMENTO');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Salada Verde', 3.99, 'ACOMPANHAMENTO');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Salada Caesar', 5.99, 'ACOMPANHAMENTO');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Molho Barbecue', 9.99, 'ACOMPANHAMENTO');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Molho Ranch', 8.99, 'ACOMPANHAMENTO');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Molho Especial da Casa', 7.99, 'ACOMPANHAMENTO');
COMMIT;
--Cria as bebidas
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Coca-Cola', 7.99, 'BEBIDA');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Pepsi', 7.99, 'BEBIDA');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Sprite', 7.99, 'BEBIDA');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Suco Natural', 5.99, 'BEBIDA');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Água Mineral', 5.99, 'BEBIDA');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Chá Gelado', 9.99, 'BEBIDA');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Água de Coco', 8.99, 'BEBIDA');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Milkshake', 7.99, 'BEBIDA');
COMMIT;
--Cria as Sobremesas
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Sundae de Chocolate', 9.99, 'SOBREMESA');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Cheesecake de Morango', 12.99, 'SOBREMESA');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Petit Gateau', 11.99, 'SOBREMESA');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Sorvete de Baunilha com Calda de Caramelo', 8.99, 'SOBREMESA');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Mousse de Maracujá', 6.99, 'SOBREMESA');
INSERT INTO produto (nome_produto, preco, categoria) VALUES ('Torta de Maçã', 9.99, 'SOBREMESA');
COMMIT;