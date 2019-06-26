public class Date {
	// 定义私有访问权限的属性，只能本类直接访问
    private int year;
    private int month;
    private int day;

	// 定义私有、不可修改的静态属性
    private static final int[] DAYS_OF_MONTH = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

	// 构造方法，有对参数有效性的基本检查
    public Date(int year, int month, int day) {
        if (year < 1900 || year > 2100) {
            System.out.println("无效的年: " + year);
			return;
        }

        if (month < 1 || month > 12) {
            System.out.println("无效的月: " + month);
			return;
        }

        if (day < 0 || day > getDaysOfMonth(year, month)) {
            System.out.println("无效的日: " + day);
			return;
        }

		// 属性初始化
		// 出现了命名遮挡，所以使用 this 关键字明确
        this.year = year;
        this.month = month;
        this.day = day;
    }

	// 私有的构造方法，用于内部方法进行对象的复制
    private Date(Date other) {
		// 利用 this 调用其他构造方法，必须出现在第一行
        this(other.year, other.month, other.day);
    }

	// 给出对象的 String 形式，便于打印
    public String toString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

	// 增加指定天，没有做边界检查
    public void increase(int days) {
        if (days < 0) {
            System.out.println("不支持负数");
            return;
        }
        day += days;
        while (day > getDaysOfMonth(year, month)) {
            day -= getDaysOfMonth(year, month);
            month++;
            if (month > 12) {
                year++;
                month = 1;
            }
        }
    }

	// 减少指定天，没有做边界检查
    public void decrease(int days) {
        if (days < 0) {
            System.out.println("不支持负数");
            return;
        }
        day -= days;
        while (day < 1) {
            month--;
            if (month < 1) {
                month = 12;
                year--;
            }

            day += getDaysOfMonth(year, month);
        }
    }

	// 比较当前日期和 other 日期的大小
	// 如果当前日期靠后（较大），返回 1
	// 如果当前日期靠前（较小），返回 -1
	// 如果相等，返回 0
    public int compareTo(Date other) {
        if (year > other.year) {
            return 1;
        }
        if (year < other.year) {
            return -1;
        }

        if (month > other.month) {
            return 1;
        }
        if (month < other.month) {
            return -1;
        }

        if (day > other.day) {
            return 1;
        }
        if (day < other.day) {
            return -1;
        }

        return 0;
    }

	// 因为没有访问任何对象的属性及方法，所以定义成静态方法
    private static boolean isLeapYear(int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        } else {
            return year % 4 == 0;
        }
    }

	// 因为没有访问任何对象的属性及方法，所以定义成静态方法
    private static int getDaysOfMonth(int year, int month) {
        int days = DAYS_OF_MONTH[month - 1];
        if (month == 2 && isLeapYear(year)) {
            days = 29;
        }

        return days;
    }

	// 静态方法，返回两个日期相差的天数
	// 如果 d1 较大，返回正数
	// 如果 d1 较小，返回负数
    public static int differ(Date d1, Date d2) {
        Date big = null;
        Date small = null;
        int minus;
        if (d1.compareTo(d2) == 1) {
            big = new Date(d1);
            small = new Date(d2);
            minus = 1;
        } else {
            big = new Date(d2);
            small = new Date(d1);
            minus = -1;
        }

        int days = 0;
        while (big.compareTo(small) == 1) {
            big.decrease(1);
            days++;
        }

        return minus * days;
    }
	
	// 入口方法
	public static void main(String[] args) {
        Date date = new Date(2019, 5, 20);
        System.out.println(date.toString());
        date.decrease(20 + 30 + 31 + 28 + 31);
        System.out.println(date.toString());

        Date today = new Date(2019, 5, 20);
        Date internRecruitDate = new Date(2019, 12, 1);
        System.out.println(Date.differ(internRecruitDate, today));
    }
}