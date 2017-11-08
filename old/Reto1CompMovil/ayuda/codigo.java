 private byte[] convertirAByteArray(int[] rgbArray) {
        int width = getWidth();
        int height = getHeight();

        byte[] byteArray = new byte[rgbArray.length*4];


        for(int i=0; i<(rgbArray.length)*4; )
        {
           byteArray[i+0] = (byte)(rgbArray[i/4] >> 24);
           byteArray[i+1] = (byte)((rgbArray[i/4] >> 16) & 0x000000FF);
           byteArray[i+2] = (byte)((rgbArray[i/4] >>  8) & 0x000000FF);
           byteArray[i+3] = (byte)(rgbArray[i/4] & 0x000000FF);
           i = i+4;
        }
        return byteArray;
    }
    
    private int[] convertirARGBArray(byte[] byteArray) {

        int width = getWidth();
        int height = getHeight();

        int[] rgbArray = new int[byteArray.length/4];

        for(int i=0; i<(rgbArray.length); i++)
        {
            rgbArray[i] = 0;
            rgbArray[i] = rgbArray[i] | (byteArray[(i*4)])<<24;
            rgbArray[i] = rgbArray[i] | (byteArray[(i*4)+1])<<16;
            rgbArray[i] = rgbArray[i] | (byteArray[(i*4)+2])<<8;
            rgbArray[i] = rgbArray[i] | (byteArray[(i*4)+3]);
           
        }
        return rgbArray;
    }