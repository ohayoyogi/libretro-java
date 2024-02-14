/**
 * LibRetroBridge class
 */
package org.ohayoyogi.libretro;

public class LibRetroBridge {

    private static LibRetroBridge instance = null;
    private LibRetroEventListener listener = null;

    public void setEventListener(LibRetroEventListener listener) {
        this.listener = listener;
    }

    public void unsetEventListener() {
        listener = null;
    }

    public static void onKeyEvent(boolean pressed, int keyCode) {
        if (instance != null) {
            if (pressed) instance.listener.onKeyPressed(keyCode);
            else instance.listener.onKeyReleased(keyCode);
        }
    }

    public static void onQuit() {
        if (instance != null) {
            instance.listener.onUnload();
        }
    }


    public void refreshVideo(int[] argbPixels, int x, int y, int width, int height) {
        LibRetroBridge.refreshVideoNative(argbPixels, x, y, width, height);
    }

    public int createSMAFPlayer() {
        return LibRetroBridge.createSMAFPlayerNative();
    }

    public void setSMAFPlayerContent(int _id, int contentId) {
        System.out.println("LibRetroBridge#setSMAFPlayerContent: " + _id + ", " + contentId);
        LibRetroBridge.setSMAFPlayerContentNative(_id, contentId);
    }

    public void startSMAFPlayer(int _id) {
        LibRetroBridge.startSMAFPlayerNative(_id);
    }

    public void stopSMAFPlayer(int _id) {
        LibRetroBridge.stopSMAFPlayerNative(_id);
    }

    public void pauseSMAFPlayer(int _id) {
        LibRetroBridge.pauseSMAFPlayerNative(_id);
    }

    public void resumeSMAFPlayer(int _id) {
        LibRetroBridge.resumeSMAFPlayerNative(_id);
    }

    public void disposeSMAFPlayer(int _id) {
        LibRetroBridge.disposeSMAFPlayerNative(_id);
    }

    public int createSMAFData(byte[] data) {
        return LibRetroBridge.createSMAFDataNative(data);
    }
    public void disposeSMAFData(int _id) {
        LibRetroBridge.disposeSMAFDataNative(_id);
    }
    /**
     * Getting instance of LibRetroBridge
     * @return instance of LibRetroBridge
     */
    public static LibRetroBridge getInstance() {
        if (instance == null) {
            instance = new LibRetroBridge();
        }
        return instance;
    }

    private static native void refreshVideoNative(int[] argbPixels, int x, int y, int width, int height);

    private static native int createSMAFPlayerNative();
    private static native void setSMAFPlayerContentNative(int _id, int contentId);
    private static native void startSMAFPlayerNative(int _id);
    private static native void stopSMAFPlayerNative(int _id);
    private static native void pauseSMAFPlayerNative(int _id);
    private static native void resumeSMAFPlayerNative(int _id);
    private static native void disposeSMAFPlayerNative(int _id);

    private static native int createSMAFDataNative(byte[] data);
    private static native void disposeSMAFDataNative(int _id);
}