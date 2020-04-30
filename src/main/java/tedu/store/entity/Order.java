package tedu.store.entity;

import java.util.Date;
import java.util.Objects;

public class Order extends BaseEntity{

    private Integer oid;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvAddress;
    private Long totalPrice;
    private Date orderTime;
    private Date payTime;
    private Integer state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return oid.equals(order.oid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oid);
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getRecvName() {
        return recvName;
    }

    public void setRecvName(String recvName) {
        this.recvName = recvName;
    }

    public String getRecvPhone() {
        return recvPhone;
    }

    public void setRecvPhone(String recvPhone) {
        this.recvPhone = recvPhone;
    }

    public String getRecvAddress() {
        return recvAddress;
    }

    public void setRecvAddress(String recvAddress) {
        this.recvAddress = recvAddress;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", recvName='" + recvName + '\'' +
                ", recvPhone='" + recvPhone + '\'' +
                ", recvAddress='" + recvAddress + '\'' +
                ", totalPrice=" + totalPrice +
                ", orderTime=" + orderTime +
                ", payTime=" + payTime +
                ", state=" + state +
                '}';
    }
}
