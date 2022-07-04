# multiports
<td class="d-block comment-body markdown-body  js-comment-body">
          <p dir="auto">If I set the management port via configuration then a Tomcat will be started listening on that port when running <code class="notranslate">@WebAppConfiguration</code> tests.<br>
I.e. an <code class="notranslate">application.yml</code> with:</p>
<div class="highlight highlight-source-json notranslate position-relative overflow-auto"><pre><span class="pl-ii">management:</span>
  <span class="pl-ii">port: 8081</span></pre><div class="zeroclipboard-container position-absolute right-0 top-0">
    <clipboard-copy aria-label="Copy" class="ClipboardButton btn js-clipboard-copy m-2 p-0 tooltipped-no-delay" data-copy-feedback="Copied!" data-tooltip-direction="w" value="management:
  port: 8081" tabindex="0" role="button">
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-copy js-clipboard-copy-icon m-2">
    <path fill-rule="evenodd" d="M0 6.75C0 5.784.784 5 1.75 5h1.5a.75.75 0 010 1.5h-1.5a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-1.5a.75.75 0 011.5 0v1.5A1.75 1.75 0 019.25 16h-7.5A1.75 1.75 0 010 14.25v-7.5z"></path><path fill-rule="evenodd" d="M5 1.75C5 .784 5.784 0 6.75 0h7.5C15.216 0 16 .784 16 1.75v7.5A1.75 1.75 0 0114.25 11h-7.5A1.75 1.75 0 015 9.25v-7.5zm1.75-.25a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-7.5a.25.25 0 00-.25-.25h-7.5z"></path>
</svg>
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-check js-clipboard-check-icon color-fg-success d-none m-2">
    <path fill-rule="evenodd" d="M13.78 4.22a.75.75 0 010 1.06l-7.25 7.25a.75.75 0 01-1.06 0L2.22 9.28a.75.75 0 011.06-1.06L6 10.94l6.72-6.72a.75.75 0 011.06 0z"></path>
</svg>
    </clipboard-copy>
  </div></div>
<p dir="auto">would cause a Tomcat to start on port 8081 when running a test looking like this:</p>
<div class="highlight highlight-source-java notranslate position-relative overflow-auto"><pre><span class="pl-c1">@</span><span class="pl-c1">RunWith</span>(<span class="pl-smi">SpringJUnit4ClassRunner</span>.<span class="pl-s1">class</span>)
<span class="pl-c1">@</span><span class="pl-c1">WebAppConfiguration</span>
<span class="pl-c1">@</span><span class="pl-c1">SpringApplicationConfiguration</span>(<span class="pl-s1">classes</span> = {<span class="pl-smi">Application</span>.<span class="pl-s1">class</span>})
<span class="pl-k">public</span> <span class="pl-k">class</span> <span class="pl-smi">ATest</span> {
...
}</pre><div class="zeroclipboard-container position-absolute right-0 top-0">
    <clipboard-copy aria-label="Copy" class="ClipboardButton btn js-clipboard-copy m-2 p-0 tooltipped-no-delay" data-copy-feedback="Copied!" data-tooltip-direction="w" value="@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = {Application.class})
public class ATest {
...
}" tabindex="0" role="button" style="display: inherit;">
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-copy js-clipboard-copy-icon m-2">
    <path fill-rule="evenodd" d="M0 6.75C0 5.784.784 5 1.75 5h1.5a.75.75 0 010 1.5h-1.5a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-1.5a.75.75 0 011.5 0v1.5A1.75 1.75 0 019.25 16h-7.5A1.75 1.75 0 010 14.25v-7.5z"></path><path fill-rule="evenodd" d="M5 1.75C5 .784 5.784 0 6.75 0h7.5C15.216 0 16 .784 16 1.75v7.5A1.75 1.75 0 0114.25 11h-7.5A1.75 1.75 0 015 9.25v-7.5zm1.75-.25a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-7.5a.25.25 0 00-.25-.25h-7.5z"></path>
</svg>
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-check js-clipboard-check-icon color-fg-success d-none m-2">
    <path fill-rule="evenodd" d="M13.78 4.22a.75.75 0 010 1.06l-7.25 7.25a.75.75 0 01-1.06 0L2.22 9.28a.75.75 0 011.06-1.06L6 10.94l6.72-6.72a.75.75 0 011.06 0z"></path>
</svg>
    </clipboard-copy>
  </div></div>
<p dir="auto">This is not the intended behavior if I am correct or am I missing something?</p>
<p dir="auto">The implication I am running into (this might be a separate issue) is that if I have two tests with different application contexts then that will cause the second test to fail. Apparently because the loading of the  second application context also tries to start a Tomcat on the same port. Which obviously fails.</p>
<p dir="auto">That is, if the second test looks like:</p>
<div class="highlight highlight-source-java notranslate position-relative overflow-auto"><pre><span class="pl-c1">@</span><span class="pl-c1">RunWith</span>(<span class="pl-smi">SpringJUnit4ClassRunner</span>.<span class="pl-s1">class</span>)
<span class="pl-c1">@</span><span class="pl-c1">WebAppConfiguration</span>
<span class="pl-c1">@</span><span class="pl-c1">SpringApplicationConfiguration</span>(<span class="pl-s1">classes</span> = {<span class="pl-smi">Application</span>.<span class="pl-s1">class</span>, <span class="pl-smi">BTest</span>.<span class="pl-s1">Conf</span>.<span class="pl-s1">class</span>})
<span class="pl-k">public</span> <span class="pl-k">class</span> <span class="pl-smi">BTest</span> {
...
}</pre><div class="zeroclipboard-container position-absolute right-0 top-0">
    <clipboard-copy aria-label="Copy" class="ClipboardButton btn js-clipboard-copy m-2 p-0 tooltipped-no-delay" data-copy-feedback="Copied!" data-tooltip-direction="w" value="@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = {Application.class, BTest.Conf.class})
public class BTest {
...
}" tabindex="0" role="button">
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-copy js-clipboard-copy-icon m-2">
    <path fill-rule="evenodd" d="M0 6.75C0 5.784.784 5 1.75 5h1.5a.75.75 0 010 1.5h-1.5a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-1.5a.75.75 0 011.5 0v1.5A1.75 1.75 0 019.25 16h-7.5A1.75 1.75 0 010 14.25v-7.5z"></path><path fill-rule="evenodd" d="M5 1.75C5 .784 5.784 0 6.75 0h7.5C15.216 0 16 .784 16 1.75v7.5A1.75 1.75 0 0114.25 11h-7.5A1.75 1.75 0 015 9.25v-7.5zm1.75-.25a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-7.5a.25.25 0 00-.25-.25h-7.5z"></path>
</svg>
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-check js-clipboard-check-icon color-fg-success d-none m-2">
    <path fill-rule="evenodd" d="M13.78 4.22a.75.75 0 010 1.06l-7.25 7.25a.75.75 0 01-1.06 0L2.22 9.28a.75.75 0 011.06-1.06L6 10.94l6.72-6.72a.75.75 0 011.06 0z"></path>
</svg>
    </clipboard-copy>
  </div></div>
<p dir="auto">then running both <code class="notranslate">ATest</code> and <code class="notranslate">BTest</code> will fail one of them.</p>
<p dir="auto">So I guess the two questions are</p>
<ol dir="auto">
<li>Is a Tomcat for the management port supposed to start in the test?</li>
<li>If so, why is the second application context triggering a start of a new Tomcat?</li>
</ol>
<p dir="auto">I put some example code that illustrates this here <a href="https://github.com/sawano/spring-boot-management-port-test-issue">https://github.com/sawano/spring-boot-management-port-test-issue</a></p>
      </td>
