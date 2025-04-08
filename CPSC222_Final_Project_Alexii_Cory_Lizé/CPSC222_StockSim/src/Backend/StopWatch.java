package Backend;

/**
 * This file was taken from a solution to
 * 		CPSC 200 Lab 1 Winter 2024
 *
 * Logic for StopWatch
 *
 * @author Cory Stecyk
 * Student Number: 230154922
 * @version 2024.2.3
 */

/**
 * StopWatch class
 */
public class StopWatch
{
    private boolean isRunning;
    private double elapsed;
    private double startTime;

    // Constructor

    /**
     * Creates a new StopWatch, which is initially stopped with
     * zero elapsed time.
     */
    public StopWatch()
    {
        this.isRunning = false;
        this.elapsed = 0;
    }


    // Attributes

    /**
     * Returns the elapsed CPU time in seconds at the time of the
     * call.
     * @return Elapsed time
     */
    public double elapsed()
    {
        double now = System.nanoTime();

        if (isRunning)
        {
            this.elapsed += now - this.startTime;
            this.startTime = now;
        }
        return (elapsed)/1_000_000_000.0;
    }

    /**
     * true if and only if start has been called more recently than
     * stop.
     * @return True if Stopwatch is running, false if Stopwatch is not running
     */
    public boolean isRunning()
    {
        return isRunning;
    }


    // Actions

    /**
     * Starts the stop watch. Has no effect if the stop watch is
     * already started. Does not reset the time. Returns this
     * @return Stopwatch
     */
    public StopWatch start()
    {
        if (!isRunning)
        {
            this.isRunning = true;
            this.startTime = System.nanoTime();
        }
        return this;
    }

    /**
     * Stops the stop watch. Has no effect if the stop watch is
     * already stopped. Does not reset the time. Returns this.
     * @return Stopwatch
     */
    public StopWatch stop()
    {
        if (isRunning)
        {
            this.elapsed += System.nanoTime() - startTime;
            this.isRunning = false;
            this.startTime = 0;
        }
        return this;
    }

    /**
     * Resets the elapsed time to zero. Neither starts nor stops the
     * stop watch. Returns this.
     * @return Stopwatch
     */
    public StopWatch reset()
    {
        this.elapsed = 0;

        return this;
    }
}

