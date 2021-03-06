package se.bjurr.prnb.admin.utils;

import static com.google.common.collect.Sets.newHashSet;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.mockito.Matchers;

import se.bjurr.prnfb.listener.PrnfbRenderer;
import se.bjurr.prnfb.listener.PrnfbRenderer.REPO_PROTOCOL;

import com.atlassian.bitbucket.project.Project;
import com.atlassian.bitbucket.project.ProjectType;
import com.atlassian.bitbucket.project.ProjectVisitor;
import com.atlassian.bitbucket.pull.PullRequestRef;
import com.atlassian.bitbucket.repository.RefType;
import com.atlassian.bitbucket.repository.Repository;
import com.atlassian.bitbucket.repository.RepositoryCloneLinksRequest;
import com.atlassian.bitbucket.repository.RepositoryService;
import com.atlassian.bitbucket.util.NamedLink;

public class PullRequestRefBuilder implements PullRequestRef {
 public static PullRequestRefBuilder pullRequestRefBuilder() {
  return new PullRequestRefBuilder(null);
 }

 private String hash;
 private String id;
 private Integer projectId;
 private String projectKey;
 private Integer repositoryId = 0;
 private String repositoryName;
 private String displayId;

 private String slug;
 private PullRequestEventBuilder pullRequestEventBuilder;

 private PullRequestRefBuilder() {
 }

 private PullRequestRefBuilder(PullRequestEventBuilder pullRequestEventBuilder) {
  this.pullRequestEventBuilder = pullRequestEventBuilder;
 }

 public static PullRequestRefBuilder pullRequestRefBuilder(PullRequestEventBuilder pullRequestEventBuilder) {
  return new PullRequestRefBuilder(pullRequestEventBuilder);
 }

 public PullRequestEventBuilder build() {
  return pullRequestEventBuilder;
 }

 @Override
 public String getDisplayId() {
  return displayId;
 }

 @Override
 public String getId() {
  return id;
 }

 @Override
 public String getLatestCommit() {
  return hash;
 }

 @Override
 public Repository getRepository() {
  return new Repository() {

   @Override
   public String getHierarchyId() {
    return null;
   }

   @Override
   public int getId() {
    return repositoryId;
   }

   @Override
   public String getName() {
    return repositoryName;
   }

   @Override
   public Repository getOrigin() {
    return null;
   }

   @Override
   public Project getProject() {
    return new Project() {

     @Override
     public <T> T accept(ProjectVisitor<T> arg0) {
      return null;
     }

     @Override
     public String getDescription() {
      return null;
     }

     @Override
     public int getId() {
      return projectId;
     }

     @Override
     public String getKey() {
      return projectKey;
     }

     @Override
     public String getName() {
      return null;
     }

     @Override
     public ProjectType getType() {
      return null;
     }

     @Override
     public boolean isPublic() {
      return false;
     }
    };
   }

   @Override
   public String getScmId() {
    return null;
   }

   @Override
   public String getSlug() {
    return slug;
   }

   @Override
   public State getState() {
    return null;
   }

   @Override
   public String getStatusMessage() {
    return null;
   }

   @Override
   public boolean isFork() {
    return false;
   }

   @Override
   public boolean isForkable() {
    return false;
   }

   @Override
   public boolean isPublic() {
    return false;
   }
  };
 }

 public PullRequestRefBuilder withHash(String pullRequestHash) {
  this.hash = pullRequestHash;
  return this;
 }

 public PullRequestRefBuilder withId(String id) {
  this.id = id;
  return this;
 }

 public PullRequestRefBuilder withProjectId(Integer projectId) {
  this.projectId = projectId;
  return this;
 }

 public PullRequestRefBuilder withProjectKey(String projectKey) {
  this.projectKey = projectKey;
  return this;
 }

 public PullRequestRefBuilder withRepositoryId(Integer repositoryId) {
  this.repositoryId = repositoryId;
  return this;
 }

 public PullRequestRefBuilder withRepositoryName(String repositoryName) {
  this.repositoryName = repositoryName;
  return this;
 }

 public PullRequestRefBuilder withRepositorySlug(String repositorySlug) {
  this.slug = repositorySlug;
  return this;
 }

 public PullRequestRefBuilder withCloneUrl(PrnfbRenderer.REPO_PROTOCOL protocol, String url) {
  mockCloneUrl(protocol, pullRequestEventBuilder.getPrnfbTestBuilder().getRepositoryService(), url);
  return this;
 }

 public PullRequestRefBuilder withDisplayId(String displayId) {
  this.displayId = displayId;
  return this;
 }

 private void mockCloneUrl(REPO_PROTOCOL protocol, RepositoryService repositoryService, final String url) {
  NamedLink e = new NamedLink() {
   @Override
   public String getName() {
    return null;
   }

   @Override
   public String getHref() {
    return url;
   }
  };
  Set<NamedLink> value = newHashSet(e);
  when(repositoryService.getCloneLinks(Matchers.any(RepositoryCloneLinksRequest.class))).thenReturn(value);
 }

 @Override
 public RefType getType() {
  return null;
 }
}