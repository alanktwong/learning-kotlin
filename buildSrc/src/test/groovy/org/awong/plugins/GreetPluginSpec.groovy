package org.awong.plugins

import spock.lang.Specification

class GreetPluginSpec extends Specification
{
    def 'should work'() {
        expect:
        new GreetPlugin() != null
    }
}
