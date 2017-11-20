package cn.gelk.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * io流工具类
 */
public class IOUtils {
    private static final ThreadLocal<ByteBuffer> localBuffer = new ThreadLocal<ByteBuffer>() {
        @Override
        protected ByteBuffer initialValue() {
            return ByteBuffer.allocate(1024);
        }
    };

    public static String readStream(InputStream input, String encoding) {
        try {
            return org.apache.commons.io.IOUtils.toString(input, encoding);
        }
        catch (IOException e) {
            System.err.println("IOException occurred while transferring data.");
        }

        return "";
    }

    public static final void copyStream(InputStream input, OutputStream output) {
        copyStream(input, output, Long.MAX_VALUE);
    }

    public static final void copyStream(InputStream input, OutputStream output, long contentLength) {
        ReadableByteChannel in = Channels.newChannel(input);
        WritableByteChannel out = Channels.newChannel(output);
        try {
            doTransfer(in, out, contentLength);
            in.close();
        }
        catch (IOException e) {
            System.err.println("IOException occurred while transferring data.");
        }
        localBuffer.remove();
    }

    public static final void closeQuietly(Closeable stream) {
        if (null != stream) {
            try {
                stream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static final void doTransfer(ReadableByteChannel in, WritableByteChannel out, long contentLength) throws IOException {
        long bytesRead = 0, totalBytesRead = 0;
        ByteBuffer buffer = localBuffer.get();

        do {
            bytesRead = in.read(buffer);
            if (-1 == bytesRead || totalBytesRead >= contentLength) {
                break;
            }

            buffer.flip();
            out.write(buffer);

            totalBytesRead += bytesRead;
            buffer.compact();
        }
        while (true);
    }
}