# t1testtask
Test task for T1Consalting

 Программа принимает на вход JSON с помощью POST запроса по адресу '/analize', где JSON содержит ключ 'analize' и в качестве значения строку, которую нужно проанализировать, например {"analize":"daabbbbccc"} и возвращает JSON, в котором ключами выступают встретившиеся хотябы один раз в строке символы, а значениями - количество вхождений этих символов в строке. Сортировка осуществлена в порядке убывания количества вхождений. Отправить такой запрос можно через программу Postman.
 Пример того, как это должно выглядеть в программе Postman:
![image](https://github.com/sbnetaa/t1testtask/assets/108130286/d81b71ec-d49d-4534-91ac-24c73725f9ac)
