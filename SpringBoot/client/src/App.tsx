import { Switch, Route } from "wouter";
import { queryClient } from "./lib/queryClient";
import { QueryClientProvider } from "@tanstack/react-query";
import { Toaster } from "@/components/ui/toaster";
import { TooltipProvider } from "@/components/ui/tooltip";
import NotFound from "@/pages/not-found";
import Dashboard from "@/pages/dashboard";
import Books from "@/pages/books";
import Readers from "@/pages/readers";
import Borrowing from "@/pages/borrowing";
import Sidebar from "@/components/layout/sidebar";
import Header from "@/components/layout/header";
import { useState } from "react";

function Router() {
  return (
    <Switch>
      <Route path="/" component={Dashboard} />
      <Route path="/dashboard" component={Dashboard} />
      <Route path="/books" component={Books} />
      <Route path="/readers" component={Readers} />
      <Route path="/borrowing" component={Borrowing} />
      <Route component={NotFound} />
    </Switch>
  );
}

function App() {
  const [currentSection, setCurrentSection] = useState("dashboard");

  return (
    <QueryClientProvider client={queryClient}>
      <TooltipProvider>
        <div className="flex h-screen bg-slate-50">
          <Sidebar currentSection={currentSection} onSectionChange={setCurrentSection} />
          <div className="flex-1 flex flex-col overflow-hidden">
            <Header currentSection={currentSection} />
            <main className="flex-1 overflow-y-auto p-6">
              <Router />
            </main>
          </div>
        </div>
        <Toaster />
      </TooltipProvider>
    </QueryClientProvider>
  );
}

export default App;
