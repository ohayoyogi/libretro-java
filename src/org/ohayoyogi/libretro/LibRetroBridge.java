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

    /**
     * How far the pen moves for this character, or 0 if the platform has no glyph for it.
     *
     * A MIDlet's own font covers 256 characters. Everything past that -- Japanese, most
     * obviously -- has to come from whatever the device itself provides, which is the
     * frontend's business rather than the MIDP implementation's.
     */
    public static native int glyphAdvanceNative(int code, int size);

    /**
     * Draw one character into a surface, and return how far the pen moved.
     *
     * Compositing happens on the native side, into the caller's own pixel array, so no
     * per-pixel loop runs in Java.
     *
     * penX/baselineY are in surface coordinates, with baselineY on the text baseline, as
     * every glyph's bearing is expressed relative to it. The clip rectangle is applied
     * here, so the caller does not need to clip first.
     *
     * Returns 0 without drawing when the platform has no glyph.
     */
    public static native int drawGlyphNative(int[] surface, int stride,
                                             int penX, int baselineY,
                                             int code, int size, int colour,
                                             int clipXmin, int clipYmin,
                                             int clipXmax, int clipYmax);

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