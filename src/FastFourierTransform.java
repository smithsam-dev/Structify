public class FastFourierTransform {
    // Computes the discrete Fourier transform (FFT) of a given complex vector
    public static void fft(double[] real, double[] imag) {
        int n = real.length;
        if (n <= 1) return;

        // Divide
        double[] evenReal = new double[n / 2];
        double[] evenImag = new double[n / 2];
        double[] oddReal = new double[n / 2];
        double[] oddImag = new double[n / 2];

        for (int i = 0; i < n / 2; i++) {
            evenReal[i] = real[2 * i];
            evenImag[i] = imag[2 * i];
            oddReal[i] = real[2 * i + 1];
            oddImag[i] = imag[2 * i + 1];
        }

        // Conquer
        fft(evenReal, evenImag);
        fft(oddReal, oddImag);

        // Combine
        for (int k = 0; k < n / 2; k++) {
            double kth = -2 * k * Math.PI / n;
            double wkReal = Math.cos(kth);
            double wkImag = Math.sin(kth);

            double tReal = wkReal * oddReal[k] - wkImag * oddImag[k];
            double tImag = wkReal * oddImag[k] + wkImag * oddReal[k];

            real[k] = evenReal[k] + tReal;
            imag[k] = evenImag[k] + tImag;
            real[k + n / 2] = evenReal[k] - tReal;
            imag[k + n / 2] = evenImag[k] - tImag;
        }
    }

    public static void main(String[] args) {
        // Input signal: a simple square wave pulse
        double[] real = {1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0};
        double[] imag = new double[8]; // All zeros initially

        fft(real, imag);

        System.out.println("FFT Results (Real + Imaginary):");
        for (int i = 0; i < real.length; i++) {
            System.out.printf("Bin %d: %6.3f + %6.3fi\n", i, real[i], imag[i]);
        }
    }
}
