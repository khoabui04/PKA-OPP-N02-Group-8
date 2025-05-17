    // Constructor
    public title(String titlename, String page, String date) {
        this.titlename = titlename;
        this.page = page;
        this.date = date;
    }

    // Getter and Setter methods
    public String getTitlename() {
        return titlename;
    }

    public void setTitlename(String titlename) {
        this.titlename = titlename;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Display information
    public void displayInfo() {
        System.out.println("Tên tiêu đề: " + titlename);
        System.out.println("Số trang: " + page);
        System.out.println("Ngày xuất bản: " + date);
    }
}
