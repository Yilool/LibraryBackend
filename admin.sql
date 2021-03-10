/** Admin create: contraseña: 1234 */
INSERT INTO `Library`.`LibraryUser` (`id`, `birthdate`, `dni`, `name`, `phonenumber`, `surname`) 
VALUES ('1', '17/09/2001', 'X00000000', 'yiyi', '000000000', 'yiyi');
INSERT INTO `Library`.`SecurityUser` (`id`, `authenticationAttempts`, `createTime`, `enabled`, `lastPasswordChange`, `locked`, `password`, `passwordPolicyExpDate`, `updateTime`, `username`, `libraryuser_id`) 
VALUES ('1', '3', '2021-03-10 07:59:30', b'1', '2021-03-10 07:59:30', b'0', '$2a$10$QskCb2yBJSR5Ldhv7nYRbu39.6Gjk3GPd0Jl37zXHver0rzhgRyd6', '2022-03-10 07:59:30', '2021-03-10 07:59:30', 'yiyi', '1');
INSERT INTO `Library`.`SecurityUser_roles` (`SecurityUser_id`, `roles`) 
VALUES ('1', 'ADMIN');

/** User create: contraseña: 1234 */
INSERT INTO `Library`.`LibraryUser` (`id`, `birthdate`, `dni`, `name`, `phonenumber`, `surname`) 
VALUES ('2', '17/09/2001', 'C00000000', 'chen', '000000000', 'chen');
INSERT INTO `Library`.`SecurityUser` (`id`, `authenticationAttempts`, `createTime`, `enabled`, `lastPasswordChange`, `locked`, `password`, `passwordPolicyExpDate`, `updateTime`, `username`, `libraryuser_id`) 
VALUES ('2', '3', '2021-03-10 07:59:30', b'1', '2021-03-10 07:59:30', b'0', '$2a$10$QskCb2yBJSR5Ldhv7nYRbu39.6Gjk3GPd0Jl37zXHver0rzhgRyd6', '2022-03-10 07:59:30', '2021-03-10 07:59:30', 'chen', '2');
INSERT INTO `Library`.`SecurityUser_roles` (`SecurityUser_id`, `roles`) 
VALUES ('2', 'USER');

/** Bookshelves create */
INSERT INTO `Library`.`BookShelf` (`id`, `genre`) VALUES ('1', 'Acción');
INSERT INTO `Library`.`BookShelf` (`id`, `genre`) VALUES ('2', 'Misterio');
INSERT INTO `Library`.`BookShelf` (`id`, `genre`) VALUES ('3', 'Aventura');
INSERT INTO `Library`.`BookShelf` (`id`, `genre`) VALUES ('4', 'Fantasía');
INSERT INTO `Library`.`BookShelf` (`id`, `genre`) VALUES ('5', 'Romance');
INSERT INTO `Library`.`BookShelf` (`id`, `genre`) VALUES ('6', 'Comedia');
INSERT INTO `Library`.`BookShelf` (`id`, `genre`) VALUES ('7', 'Ciencia Ficción');
INSERT INTO `Library`.`BookShelf` (`id`, `genre`) VALUES ('8', 'Policíaca');
INSERT INTO `Library`.`BookShelf` (`id`, `genre`) VALUES ('9', 'Paranormal');
INSERT INTO `Library`.`BookShelf` (`id`, `genre`) VALUES ('10', 'Drama');

/** Books create */
INSERT INTO `Library`.`Book` (`id`, `author`, `borrow`, `edition`, `isbn`, `pages`, `publisher`, `title`, `bookShelf`) 
VALUES ('1', 'YiChen', b'0', '1ª', 'X0000000000X', '25', 'Chen', 'Mi primer libro', '1');
INSERT INTO `Library`.`Book` (`id`, `author`, `borrow`, `edition`, `isbn`, `pages`, `publisher`, `title`, `bookShelf`) 
VALUES ('2', 'YiChen', b'0', '1ª', 'Z0000000000Z', '25', 'Chen', 'Mi segundo libro', '3');
INSERT INTO `Library`.`Book` (`id`, `author`, `borrow`, `edition`, `isbn`, `pages`, `publisher`, `title`, `bookShelf`) 
VALUES ('3', 'YiChen', b'0', '1ª', 'Y0000000000Y', '25', 'Chen', 'Mi tercer libro', '5');
INSERT INTO `Library`.`Book` (`id`, `author`, `borrow`, `edition`, `isbn`, `pages`, `publisher`, `title`, `bookShelf`) 
VALUES ('4', 'YiChen', b'0', '1ª', 'W0000000000W', '25', 'Chen', 'Mi cuarto libro', '7');
INSERT INTO `Library`.`Book` (`id`, `author`, `borrow`, `edition`, `isbn`, `pages`, `publisher`, `title`, `bookShelf`) 
VALUES ('5', 'YiChen', b'0', '1ª', 'V0000000000V', '25', 'Chen', 'Mi libro final', '9');