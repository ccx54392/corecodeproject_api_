
INSERT INTO user (user_id, user_name, password, role) VALUES (1, 'BookLover3945','12345','user');
INSERT INTO user (user_id, user_name, password, role) VALUES (2, 'BookWorm4928','12345','user');
INSERT INTO user (user_id, user_name, password, role) VALUES (3, 'Sherlock5038','12345','user');
INSERT INTO user (user_id, user_name, password, role) VALUES (4, 'CrimeAndMistery5938','12345','user');
INSERT INTO user (user_id, user_name, password, role) VALUES (5, 'Poe3103','12345','user');


INSERT INTO writer (writer_id, name) VALUES (1, 'George Orwell');
INSERT INTO writer (writer_id, name) VALUES (2, 'Harper Lee');
INSERT INTO writer (writer_id, name) VALUES (3, 'Scott Fitzgerald');
INSERT INTO writer (writer_id, name) VALUES (4, 'Gabriel Garcia Marquez');
INSERT INTO writer (writer_id, name) VALUES (5, 'Miguel de Cervantes');

INSERT INTO publisher (publisher_id, name) VALUES (1, 'Secker & Warburg');
INSERT INTO publisher (publisher_id, name) VALUES (2, 'J. B. Lippincott & Co.');
INSERT INTO publisher (publisher_id, name) VALUES (3, 'Charles Scribners Sons');
INSERT INTO publisher (publisher_id, name) VALUES (4, 'Editorial Sudamericana');
INSERT INTO publisher (publisher_id, name) VALUES (5, 'Francisco de Robles');

INSERT INTO book (book_id, title, writer_id, publisher_id, writer_writer_id,publisher_publisher_id, description) VALUES (1, '1984',1,1,1,1,'Nineteen Eighty-Four: A Novel, often published as 1984, is a dystopian social science fiction novel by English novelist George Orwell. It was published on 8 June 1949 by Secker & Warburg as Orwells ninth and final book completed in his lifetime.');
INSERT INTO book (book_id, title, writer_id, publisher_id, writer_writer_id,publisher_publisher_id, description) VALUES (2, 'To Kill a Mockingbird',2,2,2,2,'To Kill a Mockingbird is a novel by the American author Harper Lee. It was published in 1960 and, instantly successful in the United States, it is widely read in high schools and middle schools. To Kill a Mockingbird has become a classic of modern American literature, winning the Pulitzer Prize.');
INSERT INTO book (book_id, title, writer_id, publisher_id, writer_writer_id,publisher_publisher_id, description) VALUES (3, 'The Great Gatsby',3,3,3,3,'The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, the novel depicts narrator Nick Carraways interactions with mysterious millionaire Jay Gatsby and Gatsbys obsession to reunite with his former lover, Daisy Buchanan.');
INSERT INTO book (book_id, title, writer_id, publisher_id, writer_writer_id,publisher_publisher_id, description) VALUES (4, 'One Hundred Years of Solitude',4,4,4,4,'One Hundred Years of Solitude is a landmark 1967 novel by Colombian author Gabriel García Márquez that tells the multi-generational story of the Buendía family, whose patriarch, José Arcadio Buendía, founded the town of Macondo. The novel is often cited as one of the supreme achievements in literature.');
INSERT INTO book (book_id, title, writer_id, publisher_id, writer_writer_id,publisher_publisher_id, description) VALUES (5, 'Don Quixote',5,5,5,5,'The Ingenious Gentleman Don Quixote of La Mancha, or just Don Quixote, is a Spanish novel by Miguel de Cervantes. It was originally published in two parts, in 1605 and 1615.');

INSERT INTO review (review_id, stars, description, user_id, book_id,book_book_id,user_user_id) VALUES (1, 5, 'I love this book',1,1,1,1);
INSERT INTO review (review_id, stars, description, user_id, book_id,book_book_id,user_user_id) VALUES (2, 3, 'It is predictable',2,1,1,2);
INSERT INTO review (review_id, stars, description, user_id, book_id,book_book_id,user_user_id) VALUES (3, 5, 'Very interesting',3,2,2,3);
INSERT INTO review (review_id, stars, description, user_id, book_id,book_book_id,user_user_id) VALUES (4, 2, 'Couldnt read past page 1',4,2,2,4);
INSERT INTO review (review_id, stars, description, user_id, book_id,book_book_id,user_user_id) VALUES (5, 5, 'Best book ever',5,3,3,5);
INSERT INTO review (review_id, stars, description, user_id, book_id,book_book_id,user_user_id) VALUES (6, 4, 'Not my favorite book',1,3,3,1);
INSERT INTO review (review_id, stars, description, user_id, book_id,book_book_id,user_user_id) VALUES (7, 5, 'Must read',2,4,4,2);
INSERT INTO review (review_id, stars, description, user_id, book_id,book_book_id,user_user_id) VALUES (8, 4, 'Not worth it',3,4,4,3);

INSERT INTO user (user_id, user_name, password, role) VALUES (6, 'admin','admin','admin');
INSERT INTO user (user_id, user_name, password, role) VALUES (7, 'user','user','user');