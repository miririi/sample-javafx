package org.example.network;

public abstract class Packet {

    public static enum PacketTypes {
        INVALID(-1), LOGIN(00), DISCONNECT(01), MOVE(02);
        private int packetID;

        private PacketTypes(int packetID){ this.packetID = packetID;}

        public int getID() { return packetID;}
    }

    public byte packetID;

    public Packet(int packetID) { this.packetID = (byte) packetID;}

    public abstract void writeData(GameClient client);

    public abstract void writeData(GameServer server);

    public String readData(byte[] data){
        String message = new String(data).trim();
        return message.substring(2);
    }

    public abstract byte[] getData();

    public static PacketTypes lookupPacket(String packetID){
        try{
            return lookupPacket(Integer.parseInt(packetID));
        }catch(NumberFormatException e){
            return PacketTypes.INVALID;
        }
    }

    public static PacketTypes lookupPacket(int id){
        for(PacketTypes p: PacketTypes.values()){
            if(p.getID() == id){
                return p;
            }
        }
        return PacketTypes.INVALID;
    }


}
