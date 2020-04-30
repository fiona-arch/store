package tedu.store.entity.vo;

import java.io.Serializable;
import java.util.Objects;

public class CartVO  implements Serializable {

    private Integer cid;
    private Integer uid;
    private Long gid;
    private String image;
    private String title;
    private Integer num;
    private Long price;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartVO cartVO = (CartVO) o;
        return Objects.equals(cid, cartVO.cid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid);
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", gid=" + gid +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", num=" + num +
                ", price=" + price +
                '}';
    }
}
