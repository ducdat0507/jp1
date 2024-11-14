package Entity;

public class CRStatistics {
    private int id;
    private int month;
    private int year;
    private int totalView;
    private int totalAddToCart;
    private int totalCheckOut;

    public CRStatistics () {}
    public CRStatistics(int id, int month, int year) {
        this.id = id;
        this.month = month;
        this.year = year;
    }
    public CRStatistics(int id, int month, int year, int totalView, int totalAddToCart, int totalCheckOut) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.totalView = totalView;
        this.totalAddToCart = totalAddToCart;
        this.totalCheckOut = totalCheckOut;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getTotalView() {
        return totalView;
    }
    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }
    public int getTotalAddToCart() {
        return totalAddToCart;
    }
    public void setTotalAddToCart(int totalAddToCart) {
        this.totalAddToCart = totalAddToCart;
    }
    public int getTotalCheckOut() {
        return totalCheckOut;
    }
    public void setTotalCheckOut(int totalCheckOut) {
        this.totalCheckOut = totalCheckOut;
    }

    @Override
    public int hashCode() {
        final int prime = 101;
        int result = 1;
        result = prime * result + id;
        result = prime * result + month;
        result = prime * result + year;
        result = prime * result + totalView;
        result = prime * result + totalAddToCart;
        result = prime * result + totalCheckOut;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CRStatistics other = (CRStatistics) obj;
        if (id != other.id)
            return false;
        if (month != other.month)
            return false;
        if (year != other.year)
            return false;
        if (totalView != other.totalView)
            return false;
        if (totalAddToCart != other.totalAddToCart)
            return false;
        if (totalCheckOut != other.totalCheckOut)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "CRStatistics [id=" + id + ", month=" + month + ", year=" + year + ", totalView=" + totalView
                + ", totalAddToCart=" + totalAddToCart + ", totalCheckOut=" + totalCheckOut + "]";
    }
    
}
