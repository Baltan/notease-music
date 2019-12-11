package com.baltan.notease.music.domain.response;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-11 10:52
 */
public class Privilege {
    private Integer fee;
    private Integer payed;
    private Integer pl;
    private Integer dl;

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getPayed() {
        return payed;
    }

    public void setPayed(Integer payed) {
        this.payed = payed;
    }

    public Integer getPl() {
        return pl;
    }

    public void setPl(Integer pl) {
        this.pl = pl;
    }

    public Integer getDl() {
        return dl;
    }

    public void setDl(Integer dl) {
        this.dl = dl;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "fee=" + fee +
                ", payed=" + payed +
                ", pl=" + pl +
                ", dl=" + dl +
                '}';
    }
}
