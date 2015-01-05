package org.openscore.content.ssh.utils.simulator.visualization;

import java.io.InputStream;

public interface IShellVisualizer {

    public void run(InputStream in);

    public String getXMLSummary();
}