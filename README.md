https://gitlab.com/epam-dnipro-hiring-challenge/hiring-event-2019-1/coding-test/wikis/Password%20hash%20cracking

# Password hash cracking

Find the password of the following MD5 hash value: `69c459dd76c6198f72f0c20ddd3c9447` Use the brute-force approach and assume that the password consists of lowercase letters from the English alphabet. Run the calculation on multiple threads in order to get the result faster.

Codebase can be found below.

The application should run multiple threads to try out every possible combination. The threads should run in parallel to achieve the best performance on the CPUs and it must be ensured that every combination is only tried once. (If one thread already tried a combination, the other threads should not try it again). Once the password is found all threads must stop.

At the end the program should output the result and the time it took to crack the code.

To use the HashCalculator class, download the Guava library here:

Jar file: http://search.maven.org/remotecontent?filepath=com/google/guava/guava/21.0/guava-21.0.jar

Maven dependency: https://mvnrepository.com/artifact/com.google.guava/guava/21.0

```
package com.epam.training; 

import java.nio.charset.StandardCharsets; 
import com.google.common.hash.Hasher; 
import com.google.common.hash.Hashing; 

public class HashCalculator { 
    public String hash(String toHash) { 
        Hasher hasher = Hashing.md5().newHasher(); 
        hasher.putString(toHash, StandardCharsets.UTF_8); 
        return hasher.hash().toString(); 
    } 
}
```

More complicated task: `4fd0101ea3d0f5abbe296ef97f47afec`

Answers:

regular task - zebra

complicated task - titkos 

========================================================

https://gitlab.com/epam-dnipro-hiring-challenge/hiring-event-2019-1/coding-test/wikis/Chat%20bot%20challenge

# Chat-bot challenge

Кандидату необходимо разработать консольный бот-чат.

## Базовый вариант:

При запуске, программе передаётся стратегия выбора ответов и файл с набором фраз.  
После инициализации бот начинает диалог с приветствия (предопределённое значение) и ожидает ввода фразы собеседника.  
На каждую фразу бот должен ответить согласно выбранной стратегии ответов.  
Необходимо предусмотреть три алгоритма выбора ответов из файла с набором фраз, и имплементировать один из них (оставив возможность для расширения):

* **rand** – случайный выбор;
* **upseq** – последовательность сверху-вниз;
* **downseq** – последовательность снизу вверх;

Консольный ввод позволяет использовать две макро-команды:  
**calculate**: математическое_выражение

* Математическое выражение подразумевает только операцию сложения двух положительных чисел
* Математическое выражение может содержать (а может и не содержать) пробелы
* Ответ: Я тебя полюбил — я тебя научу: %результат%
* Ошибка в мат.выражении: У тебя в голове мозги или кю?!

**strategy**: rand/upseq/downseq,

* Ответ: Как советовать, так все чатлане. Использую: %стратегия%
* Ошибка: У тебя в голове мозги или кю?!

## Словарь:

* Содержит однострочные фразы.
* Каждая фраза начинается с: >
* Размер словаря: до 1G

## Пример словаря:

```
~> cat ~/answers.txt 
> Товарищ, там человек говорит, что он — инопланетянин. Надо что-то делать… 
> Не надо булочной. Не надо справочной. 
> Gentlemen, sorry! We haven’t got money. 
> Люсенька, родная, зараза, сдались тебе эти… макароны… 
> Пошли, Скрипач, в открытый космос 
> Пацак пацака не обманывает. Это некрасиво, родной… 
> Ты не дрыгайся! Показывай свою гравицаппу. Если фирменная вещь — возьмём! 
> Это оголтелый расизм! 
> Привет! Как жизнь? 
> А Скрипач не нужен, родной. 
> Принеси песочку, родной. 
> Ищи себе другой ансамбль, дядя! 
> Я скажу всем, до чего довёл планету этот фигляр ПЖ! 
> Пацаки чатланам на голову сели!  
> Кю! 
> Дядя Вова. Цапу надо крутить, цапу. 
 > На! Сам делай! 
> Мне нельзя, я чатланин. 
> Уйди отсюда!!! Как советовать, так все чатлане, а как работать, так… 
> Женщину вынули, автомат засунули. 
> Strangers in the ку… 
> Всем лежать! Полчаса! 
```

\##Функциональная проверка:

```
> ~> talk_to_me -r rand -f ~/answers.txt 
> [бот] Привет. Как дела на плюке? 
> [Я] Привет.  
> [бот] Люсенька, родная, зараза, сдались тебе эти… макароны… 
> [Я] посчитай: 5+5 
>  [бот] Я тебя полюбил — я тебя научу: 10 
>  [Я] стратегия: upseq 
> [бот] Как советовать, так все чатлане. Использую стратегию: upseq 
> [Я] посчитай: 5 № 5  
>  [бот] У тебя в голове мозги или кю?! 
 
```

## Вариант усложнения:

Добавить боту поведение:

* При отсутствии активности N (N - конф. параметр) секунд постить в чат сообщение:
* На речке, на речке, на том бережо-очке, ку, Марусенька белые ыыыы!..
* Реализовать вычитание/умножение/деление в операциях макро-команды посчитай
* Реализовать оставшиеся алгоритмы выбора ответов
* Реализовать поддержку многострочных фраз словаря

## Дополнительно учитываются\*:

* Обработка ошибочного ввода макро-команд
* Обработка битого словаря (бинарный файл вместо текста), пропуск пустых строк
* Оптимизация работы с большим словарём
* Конфигурация зарезервированных фраз бота
* Гибкость и возможность расширения макро-команд (новые стратегии/выражения)
* знание первоисточника словаря бота – отдельный плюс =)

Словарь - [answers.txt](coding\src\main\resources\answers.txt)