import java.util.Arrays;
import java.util.Comparator;

class Student implements Comparable<Student> {
    public int id;
    public String name;
    public int age;
    public int height;
    public int weight;

    public Student(int id, String name, int age, int height, int weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    // 自然顺序是按 id 排序
    @Override
    public int compareTo(Student other) {
        return id - other.id;
    }

    // equals 和 hashCode 也要同步实现
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o.getClass() != Student.class) {
            return false;
        }

        return compareTo((Student)o) == 0;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(id).hashCode();
    }

    @Override
    public String toString() {
        return String.format("%nStudent{%n"
                           + "  id=%d%n"
                           + "  name=%s%n"
                           + "  age=%d%n"
                           + "  height=%d%n"
                           + "  weight=%d%n"
                           + "}", id, name, age, height, weight);
    }
}

// 按身高
class SortByHeight implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.height - s2.height;
    }
}

// 优先按年龄，如果年龄相等，再按身高
class SortByAgeHeight implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        if (s1.age != s2.age) {
            return s1.age - s2.age;
        } else {
            return s1.height - s2.height;
        }
    }
}

public class CAC {
    public static void main(String[] args) {
        Student[] students = {
            new Student(3, "猪悟能", 500, 173, 97),
            new Student(6, "鲁智深", 34, 188, 85),
            new Student(1, "刘玄德", 53, 183, 92),
            new Student(7, "武松", 33, 187, 82),
            new Student(5, "孙悟空", 500, 152, 45),
            new Student(8, "贾宝玉", 17, 171, 62),
            new Student(2, "曹孟德", 49, 163, 57),
            new Student(4, "沙悟净", 500, 193, 87),
        };

        System.out.println("原始顺序：" + Arrays.toString(students));

        // 没有指定排序标准，按自然顺序排序，即 Comparable 接口中的 compareTo 方法
        Arrays.sort(students);
        System.out.println("按自然顺序排序后：" + Arrays.toString(students));

        // 指定了按身高排序，即实现了 Comparator 接口的类
        Arrays.sort(students, new SortByHeight());
        System.out.println("按身高顺序排序后：" + Arrays.toString(students));

        // 优先年龄，年龄相等再按身高
        Arrays.sort(students, new SortByAgeHeight());
        System.out.println("按年龄、身高顺序排序后：" + Arrays.toString(students));
    }
}
