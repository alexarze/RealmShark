package packets.incoming;

import packets.Packet;
import packets.reader.BufferReader;
import packets.data.TradeItemData;

import java.util.Arrays;

/**
 * Received when a new active trade has been initiated
 */
public class TradeStartPacket extends Packet {
    /**
     * A description of the player's inventory. Items 0-3 are the hotbar items,
     * and 4-19 are the 8 inventory slots and 8 backpack slots
     */
    public TradeItemData[] clientItems;
    /**
     * The trade partner's name.
     */
    public String partnerName;
    /**
     * A description of the trade partner's inventory. Items 0-3 are the
     * hotbar items, and 4-19 are the 8 inventory slots and 8 backpack slots
     */
    public TradeItemData[] partnerItems;
    /**
     * Object id
     */
    public int objectId;
    /**
     * Unknown
     */
    public byte unknown;

    @Override
    public void deserialize(BufferReader buffer) throws Exception {
        clientItems = new TradeItemData[buffer.readShort()];
        for (int i = 0; i < clientItems.length; i++) {
            clientItems[i] = new TradeItemData().deserialize(buffer);
        }
        partnerName = buffer.readString();
        partnerItems = new TradeItemData[buffer.readShort()];
        for (int i = 0; i < partnerItems.length; i++) {
            partnerItems[i] = new TradeItemData().deserialize(buffer);
        }
        objectId  = buffer.readInt();
        unknown  = buffer.readByte();
    }

    @Override
    public String toString() {
        return "TradeStartPacket{" +
                "\n   clientItems=" + Arrays.toString(clientItems) +
                "\n   partnerName=" + partnerName +
                "\n   partnerItems=" + Arrays.toString(partnerItems) +
                "\n   objectId=" + objectId +
                "\n   unknown=" + unknown;
    }
}