## <a name="lab-5"></a> Лабораторная работа #5

Реализовать консольное приложение, которое реализует управление коллекцией объектов в интерактивном режиме. В коллекции необходимо хранить объекты класса SpaceMarine, описание которого приведено ниже.</n>

<b>Разработанная программа должна удовлетворять следующим требованиям:</b>
<ul>
<li>Класс, коллекцией экземпляров которого управляет программа, должен реализовывать сортировку по умолчанию.</li>
<li>Все требования к полям класса (указанные в виде комментариев) должны быть выполнены.</li>
<li>Для хранения необходимо использовать коллекцию типа java.util.ArrayDequeue</li>
<li>При запуске приложения коллекция должна автоматически заполняться значениями из файла.</li>
<li>Имя файла должно передаваться программе с помощью: переменная окружения.</li>
<li>Данные должны храниться в файле в формате json</li>
<li>Чтение данных из файла необходимо реализовать с помощью класса java.io.BufferedReader</li>
<li>Запись данных в файл необходимо реализовать с помощью класса java.io.BufferedOutputStream</li>
<li>Все классы в программе должны быть задокументированы в формате javadoc.</li>
<li>Программа должна корректно работать с неправильными данными (ошибки пользовательского ввода, отсутсвие прав доступа к файлу и т.п.).</li>
</ul>
<b>В интерактивном режиме программа должна поддерживать выполнение следующих команд:</b>
<ul>
<li>help : вывести справку по доступным командам</li>
<li>info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)</li>
<li>show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении</li>
<li>add {element} : добавить новый элемент в коллекцию</li>
<li>update id {element} : обновить значение элемента коллекции, id которого равен заданному</li>
<li>remove_by_id id : удалить элемент из коллекции по его id</li>
<li>clear : очистить коллекцию</li>
<li>save : сохранить коллекцию в файл</li>
<li>execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.</li>
<li>exit : завершить программу (без сохранения в файл)</li></li>
<li>head : вывести первый элемент коллекции</li>
<li>remove_greater {element} : удалить из коллекции все элементы, превышающие заданный</li>
<li>remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный</li>
<li>remove_any_by_chapter chapter : удалить из коллекции один элемент, значение поля chapter которого эквивалентно заданному</li>
<li>count_greater_than_heart_count heartCount : вывести количество элементов, значение поля heartCount которых больше заданного</li>
<li>filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку</li>
</ul>
<b>Формат ввода команд:</b>
<ul>
<li>Все аргументы команды, являющиеся стандартными типами данных (примитивные типы, классы-оболочки, String, классы для хранения дат), должны вводиться в той же строке, что и имя команды.</li>
<li>Все составные типы данных (объекты классов, хранящиеся в коллекции) должны вводиться по одному полю в строку.</li>
<li>При вводе составных типов данных пользователю должно показываться приглашение к вводу, содержащее имя поля (например, "Введите дату рождения:")</li>
<li>Если поле является enum'ом, то вводится имя одной из его констант (при этом список констант должен быть предварительно выведен).</li>
<li>При некорректном пользовательском вводе (введена строка, не являющаяся именем константы в enum'е; введена строка вместо числа; введённое число не входит в указанные границы и т.п.) должно быть показано сообщение об ошибке и предложено повторить ввод поля.</li>
<li>Для ввода значений null использовать пустую строку.</li>
<li>Поля с комментарием "Значение этого поля должно генерироваться автоматически" не должны вводиться пользователем вручную при добавлении.</li>
</ul>
<b>Описание хранимых в коллекции классов:</b>
<pre>
<code>
public class SpaceMarine {
private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
private String name; //Поле не может быть null, Строка не может быть пустой
private Coordinates coordinates; //Поле не может быть null
private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
private float health; //Значение поля должно быть больше 0
private Integer heartCount; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 3
private AstartesCategory category; //Поле может быть null
private Weapon weaponType; //Поле может быть null
private Chapter chapter; //Поле не может быть null
}
public class Coordinates {
private double x; //Значение поля должно быть больше -412
private double y;
}
public class Chapter {
private String name; //Поле не может быть null, Строка не может быть пустой
private String parentLegion;
private Long marinesCount; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 1000
private String world; //Поле может быть null
}
public enum AstartesCategory {
DREADNOUGHT,
TACTICAL,
LIBRARIAN,
CHAPLAIN,
HELIX;
}
public enum Weapon {</n>
BOLTGUN,
MELTAGUN,
GRENADE_LAUNCHER,
MULTI_MELTA;
}
</code>
</pre>

## <a name="lab-6"></a> Лабораторная работа #6
Разделить программу из [лабораторной работы №5](#lab-5) на клиентский и серверный модули. Серверный модуль должен осуществлять выполнение команд по управлению коллекцией. Клиентский модуль должен в интерактивном режиме считывать команды, передавать их для выполнения на сервер и выводить результаты выполнения.</n>
<b>Необходимо выполнить следующие требования:</b>
<ul>
<li>Операции обработки объектов коллекции должны быть реализованы с помощью Stream API с использованием лямбда-выражений.</li>
<li>Объекты между клиентом и сервером должны передаваться в сериализованном виде.</li>
<li>Объекты в коллекции, передаваемой клиенту, должны быть отсортированы по местоположению.</li>
<li>Клиент должен корректно обрабатывать временную недоступность сервера.</li>
<li>Обмен данными между клиентом и сервером должен осуществляться по протоколу TCP.</li>
<li>Для обмена данными на сервере необходимо использовать <b>потоки ввода-вывода</b></li>
<li>Для обмена данными на клиенте необходимо использовать <b>сетевой канал</b></li>
<li>Сетевые каналы должны использоваться в неблокирующем режиме.</li>
<li>Все классы в программе должны быть задокументированы в формате javadoc.</li>
</ul>
<b>Обязанности серверного приложения:</b>
<ul>
<li>Работа с файлом, хранящим коллекцию.</li>
<li>Управление коллекцией объектов.</li>
<li>Назначение автоматически генерируемых полей объектов в коллекции.</li>
<li>Ожидание подключений и запросов от клиента.</li>
<li>Обработка полученных запросов (команд).</li>
<li>Сохранение коллекции в файл при завершении работы приложения.</li>
<li>Сохранение коллекции в файл при исполнении специальной команды, доступной только серверу (клиент такую команду отправить не может).</li>
</ul>
<b>Серверное приложение должно состоять из следующих модулей (реализованных в виде одного или нескольких классов):</b>
<ul>
<li>Модуль приёма подключений.</li>
<li>Модуль чтения запроса.</li>
<li>Модуль обработки полученных команд.</li>
<li>Модуль отправки ответов клиенту.</li>
</ul>
Сервер должен работать в <b>однопоточном</b> режиме.
<b>Обязанности клиентского приложения:</b>
<ul>
<li>Чтение команд из консоли.</li>
<li>Валидация вводимых данных.</li>
<li>Сериализация введённой команды и её аргументов.</li>
<li>Отправка полученной команды и её аргументов на сервер.</li>
<li>Обработка ответа от сервера (вывод результата исполнения команды в консоль).</li>
<li>Команду <span style="color:red">save</span> из клиентского приложения необходимо убрать.</li>
<li>Команда <span style="color:red">exit</span> завершает работу клиентского приложения.</li>
</ul>
<b>Важно!</b> Команды и их аргументы должны представлять из себя объекты классов. Недопустим обмен "простыми" строками. Так, для команды add или её аналога необходимо сформировать объект, содержащий тип команды и объект, который должен храниться в вашей коллекции.
<b>Дополнительное задание:</b>
Реализовать логирование различных этапов работы сервера (начало работы, получение нового подключения, получение нового запроса, отправка ответа и т.п.) с помощью <b>Java Util Logging</b>
