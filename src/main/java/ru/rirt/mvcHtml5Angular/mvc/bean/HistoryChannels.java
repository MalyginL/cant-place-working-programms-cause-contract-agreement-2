package ru.rirt.mvcHtml5Angular.mvc.bean;

import javax.persistence.*;

@Entity
@Table(name = "History_channels", schema = "ComparatorBase", catalog = "")
public class HistoryChannels {
    private int id;
    private int date;
    private int channel;
    private String a;
    private String b;
    private String aBrak;
    private String bBrak;
    private String channelResult;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Basic
    @Column(name = "channel")
    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    @Basic
    @Column(name = "a")
    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Basic
    @Column(name = "b")
    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Basic
    @Column(name = "a_brak")
    public String getaBrak() {
        return aBrak;
    }

    public void setaBrak(String aBrak) {
        this.aBrak = aBrak;
    }

    @Basic
    @Column(name = "b_brak")
    public String getbBrak() {
        return bBrak;
    }

    public void setbBrak(String bBrak) {
        this.bBrak = bBrak;
    }

    @Basic
    @Column(name = "channel_result")
    public String getChannelResult() {
        return channelResult;
    }

    public void setChannelResult(String channelResult) {
        this.channelResult = channelResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoryChannels that = (HistoryChannels) o;

        if (id != that.id) return false;
        if (date != that.date) return false;
        if (channel != that.channel) return false;
        if (a != null ? !a.equals(that.a) : that.a != null) return false;
        if (b != null ? !b.equals(that.b) : that.b != null) return false;
        if (aBrak != null ? !aBrak.equals(that.aBrak) : that.aBrak != null) return false;
        if (bBrak != null ? !bBrak.equals(that.bBrak) : that.bBrak != null) return false;
        if (channelResult != null ? !channelResult.equals(that.channelResult) : that.channelResult != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + date;
        result = 31 * result + channel;
        result = 31 * result + (a != null ? a.hashCode() : 0);
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (aBrak != null ? aBrak.hashCode() : 0);
        result = 31 * result + (bBrak != null ? bBrak.hashCode() : 0);
        result = 31 * result + (channelResult != null ? channelResult.hashCode() : 0);
        return result;
    }
}
